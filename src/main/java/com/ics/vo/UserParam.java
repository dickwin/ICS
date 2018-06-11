package com.ics.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tony on 2016/05/26.
 */
public class UserParam {

    private User portalUser;

    @NotNull
    @Valid
    private User tradeUser;

    private String appId;

    private String gatewayIP;

    private String localIP;

    private String realIP;

    private String phone;

    private String uuid;

    private String imei;

    private String platform;

    private String softwareVersion;
    
    private String channel;
    
    private String channelParam;   

    private String entrustWay;

	private Long entrustId;
    
    private boolean hasTradePassword;
    
    private String password;
    
    private String hdd;
    
    private String mac;  
    
    public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelParam() {
		return channelParam;
	}

	public void setChannelParam(String channelParam) {
		this.channelParam = channelParam;
	}

	public User getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(User portalUser) {
        this.portalUser = portalUser;
    }

    public User getTradeUser() {
        return tradeUser;
    }

    public void setTradeUser(User tradeUser) {
        this.tradeUser = tradeUser;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGatewayIP() {
        return gatewayIP;
    }

    public void setGatewayIP(String gatewayIP) {
        this.gatewayIP = gatewayIP;
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP(String localIP) {
        this.localIP = localIP;
    }

    public String getRealIP() {
        return realIP;
    }

    public void setRealIP(String realIP) {
        this.realIP = realIP;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getEntrustWay() {
        return entrustWay;
    }

    public void setEntrustWay(String entrustWay) {
        this.entrustWay = entrustWay;
    }

    public Long getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Long entrustId) {
        this.entrustId = entrustId;
    }

    public boolean isHasTradePassword() {
		return hasTradePassword;
	}

	public void setHasTradePassword(boolean hasTradePassword) {
		this.hasTradePassword = hasTradePassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public class User{
        @NotNull
        @Size(min = 1, message = "required id")
        private String id;

        @NotNull
        @Size(min = 1, message = "required name")
        private String name;
        
        private String mobile;

        public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
