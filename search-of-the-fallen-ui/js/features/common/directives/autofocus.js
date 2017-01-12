/**
 *  Defines the Autofocus directive.
 *  This directive used to override the original `autofocus` attribute since it doesn't work properly in SPA scenarios
 *
 *  @author  thad
 *  @date    Jan 11, 2017
 *
 */
export default {
    type: 'directive',
    name: 'autofocus',

    directiveFactory: function() {
        'ngInject';

        return {
            restrict: 'A',
            link($scope, element) {
                element[0].focus();
            }
        };
    }
};
