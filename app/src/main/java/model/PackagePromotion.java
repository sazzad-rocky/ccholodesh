package model;

/**
 * Created by Olivine on 5/10/2017.
 */

public class PackagePromotion {
    private String PromotionName;
    private String promotionDescription;
    private String promotionDeadlien;

    public String getPromotionName() {
        return PromotionName;
    }

    public PackagePromotion setPromotionName(String promotionName) {
        PromotionName = promotionName;
        return this;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public PackagePromotion setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
        return this;
    }

    public String getPromotionDeadlien() {
        return promotionDeadlien;
    }

    public PackagePromotion setPromotionDeadlien(String promotionDeadlien) {
        this.promotionDeadlien = promotionDeadlien;
        return this;
    }
}
