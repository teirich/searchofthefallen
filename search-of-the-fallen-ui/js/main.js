/**
 *  main.js manage the whole application.
 *
 *  @author  thad
 *  @date    Jan 11, 2017
 *
 */
import angular from 'angular';
import {pluck} from './fw/helper/object';
import {declareFeatures, declareValues, declareDirectives, declareComponents, declareRunners, declareFilters} from './fw/helper/ngDeclare';
import Extensions from './fw/ext/main';
import Configurators from './fw/config/main';
import Values from './fw/value/main';
import Things from './features/main';
import Application from './application';
import {Splash} from 'splash-screen';

export default class {

    constructor() {
        this.appName = 'search-of-the-fallen-ui';
        this.features = Things.filter(t => t.type === 'feature' && t.name);
    }

    validate() {
        if (!this.features || this.features.length === 0) {
            return console.warn('No features loaded');
        }

        const modNames = pluck(this.features, 'name').sort();
        for (let i = 0; i < modNames.length - 1; i++) {
            if (modNames[i] === modNames[i + 1]) {
                throw new Error('Duplicated Module: [ ' + modNames[i] + ' ], you have to specify another name');
            }
        }
    }

    findDependencies() {
        this.depends = [...Extensions, ...this.features.map(f => f.name)];
    }

    createApp() {
        declareFeatures(this.features);

        this.app = angular.module(this.appName, this.depends);
        this.app.component('application', Application);
    }

    configApp() {
        Configurators.forEach(Configurator => {
            this.app.config(Configurator.config);
        });
    }

    registerServices() {
        declareValues(this.app, Values);
        declareDirectives(this.app, Things.filter(t => t.type === 'directive'));
        declareComponents(this.app, Things.filter(t => t.type === 'component'));
        declareRunners(this.app, Things.filter(t => t.type === 'runner'));
        declareFilters(this.app, Things.filter(t => t.type === 'filter'));
    }

    destroySplash() {
        Splash.destroy();
        require('splash-screen/dist/splash.min.css').unuse();
        setTimeout(() => {
            if (Splash.isRunning()) {
                this.destroySplash();
            }
        }, 100);
    }

    launch() {
        angular.bootstrap(document, [this.appName]);
    }

    run() {
        this.validate();
        this.findDependencies();
        this.createApp();
        this.configApp();
        this.registerServices();
        this.destroySplash();
        this.launch();
    }
}
