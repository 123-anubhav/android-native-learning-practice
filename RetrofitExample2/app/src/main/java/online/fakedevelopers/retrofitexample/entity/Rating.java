package online.fakedevelopers.retrofitexample.entity;

public class Rating {

    private Double rate;

    private Integer count;

    @Override
    public String toString() {
        return "Rating{" +
                "rate=" + rate +
                ", count=" + count +
                '}';
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
