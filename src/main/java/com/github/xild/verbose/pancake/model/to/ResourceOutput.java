/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 23, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.model.to;

/**
 * @author Luis Vieira 
 *
 */
public class ResourceOutput implements TransferObject {

	private static final long serialVersionUID = 8870027544757295266L;
	
	private String message;

	@Override
	public String toString() {
		return "ResourceOutput [message=" + message + "]";
	}
	
}
