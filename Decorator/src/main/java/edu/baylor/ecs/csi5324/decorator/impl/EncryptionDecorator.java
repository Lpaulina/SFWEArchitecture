package edu.baylor.ecs.csi5324.decorator.impl;
import java.util.Base64;

import edu.baylor.ecs.csi5324.decorator.DataSource;
import edu.baylor.ecs.csi5324.decorator.DataSourceDecorator;

public class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data) {
        // decode data via Base 64
    	// use returned byte[] to do the invert of encode loop
    	// return as String
    	// byte[] result = ..
        byte[] decodedBytes = Base64.getDecoder().decode(data);
    	byte[] result = new byte[decodedBytes.length]; 
        
        for(int i = 0; i < decodedBytes.length; i++){
            result[i] = (byte) (decodedBytes[i] - 1);
        }

        return new String(result);
    }
}