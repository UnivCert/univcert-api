package com.univcert.backend.cert;

import com.univcert.backend.PropertyUtil;
import com.univcert.backend.error.UnivNotFoundException;
import org.json.simple.JSONObject;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum UnivMail {  /** 약 62개 **/

    GACHON("가천","gachon"), KANGWON("강원","kangwon"), KONKUK("건국","konkuk"), KKU("건국(글로컬)","kku"),
    GTEC("경기과학기술","gtec"),KYONGGI("경기","kyonggi"), KNU("경북","knu"), GINUE("경인교육","ginue"),
    KHU("경희","khu"), KAYWON("계원예술","kaywon"), KOREA("고려","korea"), KW("광운","kw"),
    KOOKMIN("국민","kookmin"),DANKOOK("단국","dankook"), DUKSUNG("덕성여자","duksung"), DONGGUK("동국","dongguk.edu"), DONGGUKK("동국(경주)","dongguk.ac.kr"),
    DONGDUK("동덕여자","dongduk"),MJU("명지","mju"), MJC("명지전문","mjc"), PUSAN("부산","pusan"), SANGMYUNG("상명","sangmyung"),
    SOGANG("서강","sogang"),SKUNIV("서경","skuniv"), SEOULTECH("서울과학기술","seoultech"), SNUE("서울교육","snue"),
    SNU("서울","snu"), UOS("서울시립","uos"), SWU("서울여자","swu"), SKKU("성균관","skku"),
    SUNGSHIN("성신여자","sungshin"),SJU("세종","sju"), SOOK("숙명여자","sookmyung"), SOONG("숭실","soongsil"),
    AJOU("아주","ajou"), YONSEI("연세","yonsei"), YNU("영남","ynu"), EWHA("이화여자","ewhain"),
    INU("인천","inu"),ITC("인하공전","itc"), INHA("인하","itc"), JNU("전남","jnu"),
    JBNU("전북","jbnu"), CAU("중앙","cau"), CHUNGBUK("충북","chungbuk"), KNOU("한국방송통신","knou"),
    KPU("한국산업기술","kpu"),KARTS("한국예술종합","karts"), HUFS("한국외국어","hufs"), KNSU("한국체육","knsu"),
    HANYANG("한양대학교","hanyang"), ERICA("한양에리카","hanyang"), HONGIK("홍익대학교","hongik"), DGIST("dgist","dgist"),
    GIST("gist","gist"),KAIST("카이스트","kaist"), POSTECH("포항공과","postech"), UNIST("unist","unist");

    private static final Map<String, UnivMail> UNIV_MAIL_MAP =
                    Collections.unmodifiableMap(Stream.of(values())
                        .collect(Collectors.toMap(UnivMail::getName, Function.identity())));
    private final String name;
    private final String domain;

    UnivMail(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

    public static String getDomain(String univName) {
        if (UNIV_MAIL_MAP.containsKey(univName)) {
            return UNIV_MAIL_MAP.get(univName).domain;
        }
        throw new UnivNotFoundException("존재하지 않는 대학명입니다.");
    }

    public String getName() {
        return name;
    }

    public String getDomain() {return domain;}

//    public static JSONObject domainCertify(String univ, String email){
//
//    }

    public static String changeUnivToMail(String mail){
        UnivMail[] univMails = UnivMail.values();
        for (UnivMail univMail : univMails) {
            if(univMail.name.equals(mail))
                return univMail.domain;
        }
        return "";
    }
    public static boolean certUniv(String univ, String mail){ //insi2000@mail.hongik.ac.kr
        UnivMail[] univMails = UnivMail.values();
        String[] domain = mail.split("@",2); // domain[1]에 메일 도메인 존재
        System.out.println("체크: "+ domain[1]);
        for (UnivMail univMail : univMails) {
            System.out.println(univMail.name+" ");
            if(domain[1].contains(univMail.domain) && univ.equals((univMail.name+"대학교")))
                return true;
        }
        return false;
    }


    public static boolean needCheck(String univ){  //체크할 필요가 있는 대학인지?
        UnivMail[] univMails = UnivMail.values();
        for (UnivMail univMail : univMails) {
            if(univ.equals(univMail.name+"대학교"))
                return true;
        }
        return false;
    }
}
