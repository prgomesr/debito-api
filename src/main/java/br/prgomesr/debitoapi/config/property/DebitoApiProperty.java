package br.prgomesr.debitoapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("debito")
public class DebitoApiProperty {


    private String origemPermitida = "http://localhost:4200";
    private final S3 s3 = new S3();
    private final Seguranca seguranca = new Seguranca();

    public S3 getS3() {
        return s3;
    }

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public String getOrigemPermitida() {
        return origemPermitida;
    }

    public void setOrigemPermitida(String origemPermitida) {
        this.origemPermitida = origemPermitida;
    }

    public static class S3 {
        private String accessKeyId;
        private String secrectAccessKey;
        private String bucket = "prgomesr-debito-arquivos";

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getSecrectAccessKey() {
            return secrectAccessKey;
        }

        public void setSecrectAccessKey(String secrectAccessKey) {
            this.secrectAccessKey = secrectAccessKey;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

    }

    public static class Seguranca {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }
}
