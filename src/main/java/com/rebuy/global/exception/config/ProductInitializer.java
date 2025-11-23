package com.rebuy.global.exception.config;

import com.rebuy.entity.Product;
import com.rebuy.entity.enums.ProductCategory;
import com.rebuy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(3)  // TestUserInitializer 다음에 실행
public class ProductInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() > 0) {
            System.out.println(">>> 상품 데이터가 이미 있습니다.");
            return;
        }

        List<Product> products = new ArrayList<>();

        // 식품 (FOOD)
        products.add(createProductWithImages("익사이클 바삭칩 오리지널 (45g)", "FOOD", 2000, 100, 85.5, 0.15, 0.5, 2.0, 5.0,
            new String[]{"p1_1.png", "p1_2.png", "p1_3.png"}));
        products.add(createProductWithImages("익사이클 바삭칩 핫스파이시 (45g)", "FOOD", 2500, 100, 85.5, 0.15, 0.5, 2.0, 5.0,
            new String[]{"p2_1.png", "p2_2.png", "p2_3.png"}));
        products.add(createProductWithImages("리너지가루 (밀기울분 1kg)", "FOOD", 7500, 50, 90.0, 0.3, 1.0, 5.0, 10.0,
            new String[]{"p3_1.png", "p3_2.png"}));
        products.add(createProductWithImages("금성농산 제주 못난이 흙당근 (5kg)", "FOOD", 21500, 30, 95.0, 1.2, 3.0, 10.0, 0.0,
            new String[]{"p4_1.png", "p4_2.png"}));
        products.add(createProductWithImages("서기네농장 광양 못난이 대봉감 (10kg)", "FOOD", 20200, 20, 95.0, 2.0, 5.0, 15.0, 0.0,
            new String[]{"p5_1.png", "p5_2.png", "p5_3.png"}));

        // 패션 (FASHION)
        products.add(createProductWithImages("퀼팅 아코디언 카드지갑 (체리레드)", "FASHION", 109000, 15, 88.0, 0.5, 2.0, 8.0, 50.0,
            new String[]{"p6_1.png", "p6_2.png", "p6_3.png"}));
        products.add(createProductWithImages("뉴에어맨 반지갑 (브라운)", "FASHION", 99000, 20, 88.0, 0.5, 2.0, 8.0, 50.0,
            new String[]{"p7_1.png", "p7_2.png", "p7_3.png"}));
        products.add(createProductWithImages("멀티홀더 (카드/명함) [실버]", "FASHION", 29000, 30, 85.0, 0.2, 1.0, 5.0, 30.0,
            new String[]{"p8_1.png", "p8_2.png", "p8_3.png"}));
        products.add(createProductWithImages("F05 BLAIR", "FASHION", 29000, 30, 85.0, 0.2, 1.0, 5.0, 30.0,
                new String[]{"p20_1.png", "p20_2.png", "p20_3.png"}));
        products.add(createProductWithImages("F554 MAX 0059", "FASHION", 29000, 30, 85.0, 0.2, 1.0, 5.0, 30.0,
                new String[]{"p21_1.png", "p21_2.png", "p21_3.png", "p21_4.png"}));
        products.add(createProductWithImages("F554 MAX 0024", "FASHION", 29000, 30, 85.0, 0.2, 1.0, 5.0, 30.0,
                new String[]{"p22_1.png", "p22_2.png", "p22_3.png", "p22_4.png", "p22_5.png", "p22_6.png"}));

        // 생활용품 (HOUSEHOLD)
        products.add(createProductWithImages("닥터노아 고체치약 (30정)", "HOUSEHOLD", 5900, 100, 92.0, 0.1, 0.5, 3.0, 15.0,
            new String[]{"p9_1.png", "p9_2.png"}));
        products.add(createProductWithImages("닥터노아 대나무 유아 아기 칫솔 (6개입)", "HOUSEHOLD", 19800, 80, 93.0, 0.2, 1.0, 5.0, 20.0,
            new String[]{"p10_1.png", "p10_2.png", "p10_3.png"}));
        products.add(createProductWithImages("마루 대나무 칫솔 스탠다드 (6개입)", "HOUSEHOLD", 18360, 80, 93.0, 0.2, 1.0, 5.0, 20.0,
            new String[]{"p11_1.png", "p11_2.png"}));
        products.add(createProductWithImages("닥터노아 불소 유아 아기 치약", "HOUSEHOLD", 6730, 100, 90.0, 0.1, 0.5, 3.0, 12.0,
            new String[]{"p12_1.png", "p12_2.png"}));
        products.add(createProductWithImages("닥터노아 버블 아기 바디워시&샴푸", "HOUSEHOLD", 19400, 60, 88.0, 0.3, 1.5, 8.0, 25.0,
            new String[]{"p13_1.png", "p13_2.png"}));
        products.add(createProductWithImages("제로웨이스트 대나무칫솔 밤부스토리 (4입)", "HOUSEHOLD", 5200, 100, 93.0, 0.15, 0.8, 4.0, 15.0,
            new String[]{"p14_1.png", "p14_2.png"}));
        products.add(createProductWithImages("친환경 실크 미세모 대나무 칫솔 (4+1set)", "HOUSEHOLD", 10000, 90, 93.0, 0.2, 1.0, 5.0, 18.0,
            new String[]{"p15_1.png", "p15_2.png", "p15_3.png"}));

        // 문구류 (STATIONERY)
        products.add(createProductWithImages("Fun 오렌지 친환경 노트", "STATIONERY", 19900, 50, 87.0, 0.8, 3.0, 12.0, 5.0,
            new String[]{"p16_1.png", "p16_2.png"}));
        products.add(createProductWithImages("[막세마] 리싸이클 베이 볼펜", "STATIONERY", 2600, 200, 82.0, 0.05, 0.2, 1.0, 3.0,
            new String[]{"p17_1.png", "p17_2.png", "p17_3.png"}));
        products.add(createProductWithImages("샤오미 내츄럴펜 크라프트 2p세트", "STATIONERY", 3000, 150, 82.0, 0.08, 0.3, 1.5, 4.0,
            new String[]{"p18_1.png", "p18_2.png"}));
        products.add(createProductWithImages("옥스포드 크라프트 절취 스프링노트", "STATIONERY", 1000, 300, 84.0, 0.05, 0.3, 1.5, 2.0,
            new String[]{"p19_1.png", "p19_2.png"}));

        productRepository.saveAll(products);
        System.out.println("=========== 상품 " + products.size() + "개 생성 완료 ===========");
    }

    private Product createProduct(String name, String category, int price, int stock,
                                 double ecoScore, double co2, double water, double oil, double plastic) {
        return createProductWithImages(name, category, price, stock, ecoScore, co2, water, oil, plastic, new String[]{});
    }

    private Product createProductWithImages(String name, String category, int price, int stock,
                                           double ecoScore, double co2, double water, double oil, double plastic,
                                           String[] imageFiles) {
        Product product = Product.builder()
                .name(name)
                .category(ProductCategory.valueOf(category))
                .price(BigDecimal.valueOf(price))
                .stock(stock)
                .ecoScore(BigDecimal.valueOf(ecoScore))
                .savedCo2Kg(BigDecimal.valueOf(co2))
                .savedWaterL(BigDecimal.valueOf(water))
                .savedOilMl(BigDecimal.valueOf(oil))
                .savedPlasticG(BigDecimal.valueOf(plastic))
                .build();

        // 이미지 URL 추가
        if (imageFiles != null && imageFiles.length > 0) {
            for (String imageFile : imageFiles) {
                product.getImageUrls().add("/images/products/" + imageFile);
            }
            // 대표 이미지는 첫 번째 이미지로 설정
            product.setImageUrl(product.getImageUrls().get(0));
        }

        return product;
    }
}

