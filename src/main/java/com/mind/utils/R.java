package com.mind.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class R {
	
	private String code;
	private String msg;
	private Object data;
	
	public static R succ(String mess) {
	      R m = new R();
	      m.setCode("200");
	      m.setData(null);
	      m.setMsg(mess);

	      return m;
	   }

	   public static R succ(String mess, Object data) {
	      R m = new R();
	      m.setCode("200");
	      m.setData(data);
	      m.setMsg(mess);

	      return m;
	   }

	   public static R fail(String mess) {
	      R m = new R();
	      m.setCode("400");
	      m.setData(null);
	      m.setMsg(mess);

	      return m;
	   }

	   public static R fail(String mess, Object data) {
	      R m = new R();
	      m.setCode("400");
	      m.setData(data);
	      m.setMsg(mess);

	      return m;
	   } 
}
