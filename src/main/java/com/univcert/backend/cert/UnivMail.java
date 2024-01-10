package com.univcert.backend.cert;

import com.univcert.backend.error.UnivNotFoundException;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum UnivMail {  /** 약 126개 **/

    GACHON("가천대학교","gachon"), KANGWON("강원대학교","kangwon"), KONKUK("건국대학교","konkuk"), KKU("건국대학교(글로컬)","kku"),
    GTEC("경기과학기술대학교","gtec"),KYONGGI("경기대학교","kyonggi"), KNU("경북대학교","knu"), GINUE("경인교육대학교","ginue"),
    KHU("경희대학교","khu"), KAYWON("계원예술대학교","kaywon"), KOREA("고려대학교","korea"), KW("광운대학교","kw"),
    KOOKMIN("국민대학교","kookmin"),DANKOOK("단국대학교","dankook"), DUKSUNG("덕성여자대학교","duksung"), DONGGUK("동국대학교","dongguk.edu"),
    DONGGUKK("동국대학교(경주)","dongguk.ac"), DONGDUK("동덕여자대학교","dongduk"),MJU("명지대학교","mju"), MJC("명지전문대학교","mjc"),
    SOGANG("서강대학교","sogang"),SKUNIV("서경대학교","skuniv"), SEOULTECH("서울과학기술대학교","seoultech"), SNUE("서울교육대학교","snue"),
    SNU("서울대학교","snu"), UOS("서울시립대학교","uos"), SWU("서울여자대학교","swu"), SKKU("성균관대학교","skku"),
    SUNGSHIN("성신여자대학교","sungshin"),SJU("세종대학교","sju"), SOOK("숙명여자대학교","sookmyung"), SOONG("숭실대학교","soongsil"),
    AJOU("아주대학교","ajou"), YONSEI("연세대학교","yonsei"), YNU("영남대학교","ynu"), EWHA("이화여자대학교","ewhain"),
    INU("인천대학교","inu"),ITC("인하공전대학교","itc"), INHA("인하대학교","itc"), JNU("전남대학교","jnu"),
    JBNU("전북대학교","jbnu"), CAU("중앙대학교","cau"), CHUNGBUK("충북대학교","chungbuk"), KNOU("한국방송통신대학교","knou"),
    KPU("한국산업기술대학교","kpu"),KARTS("한국예술종합대학교","karts"), HUFS("한국외국어대학교","hufs"), KNSU("한국체육대학교","knsu"),
    HANYANG("한양대학교","hanyang"), ERICA("한양대학교(ERICA)","hanyang"), HONGIK("홍익대학교","hongik"), DGIST("dgist","dgist"),
    GIST("gist","gist"),KAIST("카이스트","kaist"), POSTECH("포항공과대학교","postech"), UNIST("unist","unist"),
    KMU("계명대학교","kmu"), CHOSUN("조선대학교","chosun"), GNU("경상대학교","GNU"),DONGA("동아대학교","donga"),
    DAEGU("대구대학교","daegu"), DEU("동의대학교","deu"), CNU("충남대학교","cnu"),PUKYONG("부경대학교","pukyong"),
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
}
