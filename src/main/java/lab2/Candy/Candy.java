package lab2.Candy;

public class Candy {
    private final String id;
    private final String name;
    private final float energy;
    private final String production;
    private final String sweetType;
    private final int water;
    private final int sugar;
    private final int fructose;
    private final int vanillin;
    private final String chocolateType;
    private final float proteins;
    private final float fats;
    private final float carbohydrates;

    private Candy(CandyBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.energy = builder.energy;
        this.production = builder.production;
        this.sweetType = builder.sweetType;
        this.water = builder.water;
        this.sugar = builder.sugar;
        this.fructose = builder.fructose;
        this.vanillin = builder.vanillin;
        this.chocolateType = builder.chocolateType;
        this.proteins = builder.proteins;
        this.fats = builder.fats;
        this.carbohydrates = builder.carbohydrates;
    }


    // Методы доступа (геттеры)

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getEnergy() {
        return energy;
    }

    public String getProduction() {
        return production;
    }

    public String getSweetType() {
        return sweetType;
    }

    public int getWater() {
        return water;
    }

    public int getSugar() {
        return sugar;
    }

    public int getFructose() {
        return fructose;
    }

    public int getVanillin() {
        return vanillin;
    }

    public String getChocolateType() {
        return chocolateType;
    }

    public float getProteins() {
        return proteins;
    }

    public float getFats() {
        return fats;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public static class CandyBuilder {
        private String id;
        private String name;
        private float energy = 0.0f;
        private String production = "Unknown"; // Значение по умолчанию
        private String sweetType;
        private int water;
        private int sugar;
        private int fructose;
        private int vanillin;
        private String chocolateType;
        private float proteins;
        private float fats;
        private float carbohydrates;

        public CandyBuilder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public CandyBuilder withEnergy(float energy) {
            this.energy = energy;
            return this;
        }

        public CandyBuilder withProduction(String production) {
            this.production = production;
            return this;
        }

        public CandyBuilder withSweetType(String sweetType) {
            this.sweetType = sweetType;
            return this;
        }

        public CandyBuilder withWater(int water) {
            this.water = water;
            return this;
        }

        public CandyBuilder withSugar(int sugar) {
            this.sugar = sugar;
            return this;
        }

        public CandyBuilder withFructose(int fructose) {
            this.fructose = fructose;
            return this;
        }

        public CandyBuilder withVanillin(int vanillin) {
            this.vanillin = vanillin;
            return this;
        }

        public CandyBuilder withChocolateType(String chocolateType) {
            this.chocolateType = chocolateType;
            return this;
        }

        public CandyBuilder withProteins(float proteins) {
            this.proteins = proteins;
            return this;
        }

        public CandyBuilder withFats(float fats) {
            this.fats = fats;
            return this;
        }

        public CandyBuilder withCarbohydrates(float carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public Candy build() {
            return new Candy(this);
        }
    }

    @Override
    public String toString() {
        return "Candy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", energy=" + energy +
                ", production='" + production + '\'' +
                ", sweetType='" + sweetType + '\'' +
                ", water=" + water +
                ", sugar=" + sugar +
                ", fructose=" + fructose +
                ", vanillin=" + vanillin +
                ", chocolateType='" + chocolateType + '\'' +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
