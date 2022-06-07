package A4.G2.service.account;

import A4.G2.model.users.User;

public class ShippingDetailsManager implements IShippingDetailsManager {

    @Override
    public String getShippingAddress(User user) {
        return user.getShippingDetails().getAddress();
    }

    @Override
    public String getShippingPreferences(User user) {
        return user.getShippingDetails().getPreferences();
    }

    @Override
    public void modifyShippingAddress(User user, String newAddress) {
        user.getShippingDetails().setAddress(newAddress);
    }

    @Override
    public void modifyShippingPreferences(User user, String newPreferences) {
        user.getShippingDetails().setPreferences(newPreferences);
    }
}
