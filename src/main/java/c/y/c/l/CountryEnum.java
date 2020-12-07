package c.y.c.l;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:CountryEnum</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/7 14:55</p>
 * <p>@Version:1.0</p>
 */
public enum CountryEnum {

    ONE(1, "韩"), TWO(2, "赵"), THREE(3, "魏"), FORE(4, "楚"), FIVE(5, "燕"), SIX(6, "齐");

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CountryEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CountryEnum getCountryName(int id){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if(value.id == id){
                return value;
            }
        }
        return null;
    }

}
