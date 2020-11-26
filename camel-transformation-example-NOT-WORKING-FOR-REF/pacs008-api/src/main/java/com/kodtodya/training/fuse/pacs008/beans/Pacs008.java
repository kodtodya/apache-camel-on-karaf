package com.kodtodya.training.fuse.pacs008.beans;

import java.util.List;

public class Pacs008 {

    private Pacs008Header header;

    private List<Pacs008Details> entries;

    private Pacs008Trailer trailer;

    public Pacs008Header getHeader() {
        return header;
    }

    public void setHeader(Pacs008Header header) {
        this.header = header;
    }

    public List<Pacs008Details> getEntries() {
        return entries;
    }

    public void setEntries(List<Pacs008Details> entries) {
        this.entries = entries;
    }

    public Pacs008Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Pacs008Trailer trailer) {
        this.trailer = trailer;
    }
}
