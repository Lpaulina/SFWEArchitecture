package com.sfwe302.part2;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

class Pipe {
    private PipedReader reader;
    private PipedWriter writer;

    public Pipe() throws IOException {
        this.writer = new PipedWriter();
        this.reader = new PipedReader(this.writer);
    }

    public PipedReader getReader() {
        return reader;
    }

    public PipedWriter getWriter() {
        return writer;
    }
}
