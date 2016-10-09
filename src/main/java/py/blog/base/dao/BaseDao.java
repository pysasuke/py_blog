package py.blog.base.dao;

import py.blog.base.page.BasePage;

import java.util.List;


public interface BaseDao<T> {

	public void add(T t);

	public void update(T t);

	public void updateBySelective(T t);
	
	public void delete(Object id);

	public int queryByCount(BasePage page);
	
	public List<T> queryByList(BasePage page);
	
	public T queryById(Object id);
}
