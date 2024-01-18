package com.epam.strategy;

public interface PhoneCameraApp extends ShareStrategy {
	public boolean takePhoto();
	default boolean editPhoto() {
		return true;
	}
	public boolean savePhoto();
}
