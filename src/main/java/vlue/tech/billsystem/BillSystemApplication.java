package vlue.tech.billsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(value = {"vlue.tech.billsystem.mapper"})
@SpringBootApplication
public class BillSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillSystemApplication.class, args);
	}

}
