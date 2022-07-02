package comm.justsmile.justsite.springboot.web.cipher;

interface Cipher {
    String encode(String str);
    String decode(String str);
}
