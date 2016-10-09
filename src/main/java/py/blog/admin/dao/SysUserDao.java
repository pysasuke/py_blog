package py.blog.admin.dao;

import py.blog.admin.page.SysUserModel;
import py.blog.base.dao.BaseDao;

/**
 * SysUser Mapper
 * @author Administrator
 *
 */
public interface SysUserDao<T> extends BaseDao<T> {
	
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(SysUserModel model);
	
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
}
