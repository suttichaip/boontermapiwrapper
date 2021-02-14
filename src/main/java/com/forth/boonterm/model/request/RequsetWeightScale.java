package com.forth.boonterm.model.request;

public class RequsetWeightScale{
    private String refid;
    private String channel;
	private String mobile;

	public void setChannel(String channel){
		this.channel = channel;
	}

	public String getChannel(){
		return channel;
	}


	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid;
    }

	@Override
	public String toString() {
		return "RequsetWeightScale{" +
				"refid='" + refid + '\'' +
				", channel='" + channel + '\'' +
				", mobile='" + mobile + '\'' +
				'}';
	}
}
