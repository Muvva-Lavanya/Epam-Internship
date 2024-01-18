package com.epam.strategy;

public class TestApp {
	public static void main(String[] args) {
		ShareService strategy=new ShareService(new sharePhotoByEmail());
		strategy.sharePhoto();
	}

}
