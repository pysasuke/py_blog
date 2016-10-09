package py.blog.admin.dao;

import py.blog.admin.entity.SysRoleRel;
import py.blog.base.dao.BaseDao;

import java.util.List;


/**
 * SysRoleRel Mapper
 * @author Administrator
 *
 */
public interface SysRoleRelDao<T> extends BaseDao<T> {
	
	public void deleteByRoleId(java.util.Map<String, Object> param);
	
	public void deleteByObjId(java.util.Map<String, Object> param);
	
	
	public List<SysRoleRel> queryByRoleId(java.util.Map<String, Object> param);
	
	
	public List<SysRoleRel> queryByObjId(java.util.Map<String, Object> param);
	
	
}
