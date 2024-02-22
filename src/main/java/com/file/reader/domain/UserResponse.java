package com.file.reader.domain;

import java.util.List;
import java.util.Stack;

public class UserResponse {
	private List<UserFileResponse> userResponses;

	public List<UserFileResponse> getUserResponses() {
		return userResponses;
	}

	public void setUserResponses(List<UserFileResponse> userResponses) {
		this.userResponses = userResponses;
	}

	@Override
	public String toString() {
		return "UserResponse [userResponses=" + userResponses + "]";
	}

}
