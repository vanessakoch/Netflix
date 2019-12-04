package ifsc.edu.poo2.Netflix.database;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public interface InterfaceDAO<T> {

	public T get(String id);

	public List<T> getAll() throws UnknownHostException, IOException;

	public void add(T obj);

	public void delete(T obj);

	public void update(T obj);
}
