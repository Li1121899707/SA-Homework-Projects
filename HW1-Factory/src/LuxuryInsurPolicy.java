
// Add
public class LuxuryInsurPolicy implements PolicyProducer {
    public AutoInsurance getAutoObj() {
       return new LuxuryInsur();
    }
}
