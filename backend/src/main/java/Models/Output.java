package Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class  Output <T> implements Serializable {
    @Expose
    public List <T> data;
    @Expose
    public Paginations paginations;
    @Expose
    public Filters filters;

    public class Paginations implements Serializable{
        @Expose
        public Integer total;
        @Expose
        public Integer count;
    }

    public class Filters implements Serializable{
        @Expose
        public Integer limit;
        @Expose
        public Integer offset;
        @Expose
        public String sort;
        @Expose
        public String afterDate;
        @Expose
        public String beforeDate;
    }

    public Output() {
        this.paginations = new Paginations();
        this.filters = new Filters();
    }
}


