package py.blog.base.util;

import py.blog.base.enmu.ResponseCode;

public class ServiceException extends RuntimeException {

	ResponseCode code;

	public ServiceException(ResponseCode code){
		super();
		this.code = code;
	}

	public ServiceException(ResponseCode code, String message){
		super(message);
		this.code = code;
	}

	public ResponseCode getCode() { return this.code; }
}
