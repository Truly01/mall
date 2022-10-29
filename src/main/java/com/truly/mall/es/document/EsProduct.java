package com.truly.mall.es.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

/**
 * @author truly
 * @date 2022/10/27 22:02
 * s搜索的商品信息
 */
@Data
@Document(indexName = "pms",type = "product",shards = 1,replicas = 0)
public class EsProduct implements Serializable {
    private static final long seriaVersionUID= -1L;
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long brandId;
    private String brandName;
    @Field(type = FieldType.Keyword)
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String subTitle;
    private String keywords;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;
}
