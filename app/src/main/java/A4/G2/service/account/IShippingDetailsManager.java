package A4.G2.service.account;

import A4.G2.model.users.User;

public interface IShippingDetailsManager {

    String getShippingAddress(User user);

    String getShippingPreferences(User user);

    void modifyShippingAddress(User user, String newAddress);

    void modifyShippingPreferences(User user, String newPreferences);
}
