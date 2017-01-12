/**
 *  SSOConfig set authorised configuration.
 *
 *  @author  thad
 *  @date    Jan 11, 2017
 *
 */
export default {
    type: 'configure',
    config($httpProvider) {
        'ngInject';

        $httpProvider.defaults.headers.common.Accept = 'application/json;charset=utf-8';
        $httpProvider.defaults.withCredentials = true;
    }
};
