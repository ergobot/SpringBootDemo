package hello;

import java.util.List;

import org.springframework.data.geo.Polygon;

public interface CustomEventRepository {

	public List<Event> findInPolygon(Polygon polygon);

}