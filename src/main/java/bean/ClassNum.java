package bean;

import java.io.Serializable;

/**
 * クラス番号情報を保持するBean
 * ClassNumDao.java から学校コードに合致するデータを取得し、
 * このオブジェクトに格納して利用します。
 */
public class ClassNum implements Serializable {

    /**
     * クラス番号（DBの class_num カラムに対応）
     */
    private String class_num;

    /**
     * 所属している学校の情報（学校コード school_cd を含む School Bean）
     */
    private School school;

    /**
     * デフォルトコンストラクタ
     */
    public ClassNum() {
    }

    /**
     * クラス番号を取得します。
     * @return クラス番号（例: "101", "201" など）
     */
    public String getClass_num() {
        return class_num;
    }

    /**
     * クラス番号を設定します。
     * ClassNumDaoなどのDB操作クラスから、取得した値をセットするために使用します。
     * @param class_num クラス番号
     */
    public void setClassNum(String class_num) {
        this.class_num = class_num;
    }

    /**
     * 所属している学校の情報を取得します。
     * @return 学校情報（Schoolオブジェクト）
     */
    public School getSchool() {
        return school;
    }

    /**
     * 所属している学校の情報を設定します。
     * 学校コードに合致するデータを特定する際に、この中の school_cd を利用します。
     * @param school 学校情報（Schoolオブジェクト）
     */
    public void setSchool(School school) {
        this.school = school;
    }
}