/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 05.12.2019
 * Time: 9:36
 */

package ru.belyaev.framework;

public class FrameworkSystemException extends RuntimeException {
    public FrameworkSystemException() {
        super();
    }

    public FrameworkSystemException(String message) {
        super(message);
    }

    public FrameworkSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkSystemException(Throwable cause) {
        super(cause);
    }
}
