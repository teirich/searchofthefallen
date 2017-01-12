/**
 *  Entrance of common service
 *
 *
 *  @author  thad
 *  @date    Jan 11, 2017
 *
 */
import components from './components/main';
import directives from './directives/main';
import runners from './runners/main';

export default [...components, ...runners, ...directives];
