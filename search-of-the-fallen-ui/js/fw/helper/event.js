/**
 *  Collection of Event helpers
 *
 *  @author  thad
 *  @date    Jan 11, 2017
 *
 */
export function stop(e) {
    if (e.stopPropagation) {
        e.stopPropagation();
    }
    if (e.preventDefault) {
        e.preventDefault();
    }
}
