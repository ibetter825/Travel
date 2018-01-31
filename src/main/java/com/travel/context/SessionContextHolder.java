package com.travel.context;

import com.travel.bean.entity.User;

/**
 * ThreadLocal存取当前登录的用户
 * @author user
 *
 */
public class SessionContextHolder implements AutoCloseable {
	private static final ThreadLocal<User> currentUser = new ThreadLocal<User>();
	public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    @Override
	public void close() throws Exception {
		currentUser.remove();
	}
}
