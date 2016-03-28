import com.simcta.Role
import com.simcta.User
import com.simcta.UserRole

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
    	
        def secretaryRole = new Role('ROLE_SECRETARY').save()

        def secretary = new User('secretaria', 'secretary').save()

        UserRole.create secretary, secretaryRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }


    }
    def destroy = {
    }
}
