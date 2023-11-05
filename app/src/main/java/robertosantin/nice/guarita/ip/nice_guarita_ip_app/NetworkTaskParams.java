package robertosantin.nice.guarita.ip.nice_guarita_ip_app;

public class NetworkTaskParams {
    int tipoDisp;
    int numDisp;
    int rele;
    int geraEvt;

    public NetworkTaskParams(int tipoDisp, int numDisp, int rele, int geraEvt) {
        this.tipoDisp = tipoDisp;
        this.numDisp = numDisp;
        this.rele = rele;
        this.geraEvt = geraEvt;
    }
}