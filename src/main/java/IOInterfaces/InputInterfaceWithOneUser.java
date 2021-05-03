package IOInterfaces;

import persons.UserPerson;

public abstract class InputInterfaceWithOneUser implements IInputInterface {
    public UserPerson user = new UserPerson(0);
}
