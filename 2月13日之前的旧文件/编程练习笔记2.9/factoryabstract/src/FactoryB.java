class  FactoryB extends Factory{
    @Override
    public Product Manufacture() {
        return new ProductB();
    }
}