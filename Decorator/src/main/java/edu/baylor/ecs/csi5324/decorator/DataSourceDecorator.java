package edu.baylor.ecs.csi5324.decorator;

public class DataSourceDecorator implements DataSource {
    private DataSource wrappee;

    protected DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}