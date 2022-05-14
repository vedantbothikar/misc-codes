public class MethodOverloading {
    
    public void areaofCircle (Decimal x){
        Decimal Area=3.14*x*x;
        system.debug('Area of circle is'+Area);
    }
public void areaofRectangle (Decimal x,Decimal y){
        Decimal Area=x*y;
        system.debug('Area of Rectangle is'+Area);
    }
    public void areaofTriangle (Decimal x,Decimal y){
        Decimal Area=0.5*x*y;
        system.debug('Area of Triangle is'+Area);
    }
}

-----------------------------------------------

MethodOverloading m = new MethodOverloading();
m.areaofCircle(3.2);
m.areaofRectangle(3.2,2);
m.areaofTriangle(5,6);