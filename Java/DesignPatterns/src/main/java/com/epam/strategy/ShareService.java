package com.epam.strategy;

public class ShareService {
	private ShareStrategy shareType;

	public ShareService(ShareStrategy shareType) {
		this.shareType = shareType;
	}
	
	public void sharePhoto() {
		shareType.sharePhoto();
	}
	
	

}
