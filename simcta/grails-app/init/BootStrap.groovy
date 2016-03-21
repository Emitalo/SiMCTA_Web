import com.simcta.Role
import com.simcta.User
import com.simcta.UserRole

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
    	
	   	def directorRole = new Role('ROLE_DIRECTOR').save()
        
        def me = new User('admin', 'admin').save()

        UserRole.create me, directorRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }


    }
    def destroy = {
    }
}
