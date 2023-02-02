package com.univcert.backend.cert;

import com.univcert.backend.error.UnivNotFoundException;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum UnivMail {  /** 약 126개 **/

    GACHON("가천대학교","gachon"), KANGWON("강원대학교","kangwon"), KONKUK("건국대학교","konkuk"), KKU("건국(글로컬)대학교","kku"),
    GTEC("경기과학기술대학교","gtec"),KYONGGI("경기대학교","kyonggi"), KNU("경북대학교","knu"), GINUE("경인교육대학교","ginue"),
    KHU("경희대학교","khu"), KAYWON("계원예술대학교","kaywon"), KOREA("고려대학교","korea"), KW("광운대학교","kw"),
    KOOKMIN("국민대학교","kookmin"),DANKOOK("단국대학교","dankook"), DUKSUNG("덕성여자대학교","duksung"), DONGGUK("동국대학교","dongguk.edu"),
    DONGGUKK("동국(경주)대학교","dongguk.ac"), DONGDUK("동덕여자","dongduk"),MJU("명지","mju"), MJC("명지전문","mjc"),
    SOGANG("서강","sogang"),SKUNIV("서경","skuniv"), SEOULTECH("서울과학기술","seoultech"), SNUE("서울교육","snue"),
    SNU("서울","snu"), UOS("서울시립","uos"), SWU("서울여자","swu"), SKKU("성균관","skku"),
    SUNGSHIN("성신여자","sungshin"),SJU("세종","sju"), SOOK("숙명여자","sookmyung"), SOONG("숭실","soongsil"),
    AJOU("아주","ajou"), YONSEI("연세","yonsei"), YNU("영남","ynu"), EWHA("이화여자","ewhain"),
    INU("인천","inu"),ITC("인하공전","itc"), INHA("인하","itc"), JNU("전남","jnu"),
    JBNU("전북","jbnu"), CAU("중앙","cau"), CHUNGBUK("충북","chungbuk"), KNOU("한국방송통신","knou"),
    KPU("한국산업기술","kpu"),KARTS("한국예술종합","karts"), HUFS("한국외국어","hufs"), KNSU("한국체육","knsu"),
    HANYANG("한양대학교","hanyang"), ERICA("한양에리카","hanyang"), HONGIK("홍익대학교","hongik"), DGIST("dgist","dgist"),
    GIST("gist","gist"),KAIST("카이스트","kaist"), POSTECH("포항공과","postech"), UNIST("unist","unist"),
    KMU("계명대학교","kmu"), CHOSUN("조선대학교","chosun"), GNU("경상대학교","GNU"),DONGA("동아대학교","donga"),
    DAEGU("대구대학교","daegu"), DEU("동의대학교","deu"), CNU("충남대학교","cnu"),BUKYONG("부경대학교","bukyong"),
    ISCU("서울사이버대학교","iscu"), HYCU("한양사이버대학교","hycu"), WONKWANG("원광대학교","wonkwang"),KHCU("경희사이버대학교","khcu"),
    SDU("서울디지털대학교","sdu"), BU("백석대학교","bu"), BC("부천대학교","bc"),CU("대구가톨릭대학교","donga"),
    KYWOMAN("한양여자대학교","kywoman"), HOSEO("호서대학교","hoseo"), YJC("영진전문대학교","yjc"),KONGJU("공주대학교","kongju"),
    KS("경성대학교","ks"), SHINGU("신구대학교","shingu"), HANNAM("한남대학교","hannam"),ULSAN("울산대학교","ulsan"),
    DAELIM("대림대학교","daelim"), DSC("동서울대학교","dsc"), CJU("청주대학교","cju"),KIT("경남정보대학교","kit"),
    DONGYANG("동양미래대학교","dongyang"), DHC("대구보건대학교","dhc"), YEONSUNG("연성대학교","yeonsung"),JJ("전주대학교","jj"),
    SEOIL("서일대학교","seoil"), INDUK("인덕대학교","induk"), CUK("고려사이버대학교","cuk"),YNK("영남이공대학교","ync"),
    JANGAN("장안대학교","jangan"), SCH("순천향대학교","sch"), BSCU("백석문화대학교","bscu"),KMCU("계명문화대학교","kmcu"),
    HANMA("경남대학교","hanma"), NSU("남서울대학교","nsu"), OSAN("오산대학교","osan"),SJCU("세종사이버대학교","sjcu"),
    JEJUNU("제주대학교","jejunu"), KBU("경복대학교","kbu"), MASAN("마산대학교","masan"),SUWON("수원대학교","suwon"),
    SANGJI("상지대학교","sangji"), SSC("수원과학대학교","ssc"), DONGSEO("동서대학교","dongseo"),HIT("대전보건대학교","hit"),
    SUNMOON("선문대학교","sunmoon"), YUHAN("유한대학교","yuhan"), KIC("경인여자대학교","kic"),PCU("배재대학교","pcu"),
    SEOYOUNG("서영대학교","seoyoung"), WSU("우송대학교","wsu"), DJU("대전대학교","dju"),JMAIL("중부대학교","jmail"),
    UT("한국교통대학교","UT"), INJE("인제대학교","inje"), DIT("동의과학대학교","dit"),HANBAT("한밭대학교","hanbat"),
    HANSUNG("한성대학교","hansung"),SYUIN("삼육대학교","syuin"),KAU("한국항공대학교","kau"), SEOULARTS("서울예술대학교", "seoularts"),
    PUSAN("부산대학교","pusan"), SANGMYUNG("상명대학교","sangmyung");

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
