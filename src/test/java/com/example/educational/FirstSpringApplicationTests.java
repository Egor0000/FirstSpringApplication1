package com.example.educational;

import com.example.educational.dto.UniversityDTO;
import com.example.educational.repositories.UniversityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.BodyInserters;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;



import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient(timeout = "PT1M")//30 seconds
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FirstSpringApplicationTests {


	private String serverURL;

	@LocalServerPort
	private int port;

	private final WebTestClient webTestClient;

	@Mock
	private HttpServletRequest request;

	@BeforeAll
	public void setUp(){
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		serverURL = String.format("%s:%s", "localhost", port);

	}

	@Test
	void saveValidUniversity(){
		UniversityDTO universityDTO = new UniversityDTO();
		universityDTO.setName("Technical University");
		universityDTO.setShortName("UTM");

		//act
		UniversityDTO savedUniversity = this.webTestClient
				.post()
				.uri(serverURL + "/api/service/university/save")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(universityDTO))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(UniversityDTO.class)
				.returnResult()
				.getResponseBody();


		Assertions.assertNotNull(savedUniversity);
//		Assertions.assertEquals(universityDTO.getId(), savedUniversity.getId());
//		Assertions.assertEquals(universityDTO.getName(), savedUniversity.getName());
//		Assertions.assertEquals(universityDTO.getId(), savedUniversity.getId());
////		Assertions.assertEquals(universityDTO, res);

		HttpStatus deleteUni = this.webTestClient
				.delete()
				.uri(serverURL + "/api/service/university/deleteById/" + savedUniversity.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(HttpStatus.class)
				.returnResult()
				.getResponseBody();
	}

	@Test
	void getRequest(){
		UniversityDTO universityDTO = new UniversityDTO();
		universityDTO.setName("Technical University");
		universityDTO.setShortName("UTM");
		Long id = 1L;

		UniversityDTO savedUniversity = this.webTestClient
				.post()
				.uri(serverURL + "/api/service/university/save")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(universityDTO))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(UniversityDTO.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(savedUniversity);

		//act
		UniversityDTO getUni = this.webTestClient
				.get()
				.uri(serverURL + "/api/service/university/getById/" + savedUniversity.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(UniversityDTO.class)
				.returnResult()
				.getResponseBody();

		HttpStatus deleteUni = this.webTestClient
				.delete()
				.uri(serverURL + "/api/service/university/deleteById/" + savedUniversity.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(HttpStatus.class)
				.returnResult()
				.getResponseBody();


		Assertions.assertNotNull(getUni);
		Assertions.assertEquals(universityDTO.getId(), getUni.getId());
		Assertions.assertEquals(universityDTO.getName(), getUni.getName());
		Assertions.assertEquals(universityDTO.getId(), getUni.getId());
//		Assertions.assertEquals(universityDTO, res);
	}

	@Test
	void deleterRequest(){


		UniversityDTO universityDTO = new UniversityDTO();
		universityDTO.setId(1L);
		universityDTO.setName("Technical University");
		universityDTO.setShortName("UTM");

		UniversityDTO savedUniversity = this.webTestClient
				.post()
				.uri(serverURL + "/api/service/university/save")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(universityDTO))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(UniversityDTO.class)
				.returnResult()
				.getResponseBody();


		//act
		HttpStatus deleteUni = this.webTestClient
				.delete()
				.uri(serverURL + "/api/service/university/deleteById/" + savedUniversity.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(HttpStatus.class)
				.returnResult()
				.getResponseBody();


		Assertions.assertNotNull(savedUniversity);
	}


	private static List<UniversityDTO> getValidUniversity(){

		UniversityDTO universityDTO = new UniversityDTO();
		universityDTO.setId(1L);
		universityDTO.setName("Technical University");
		universityDTO.setShortName("UTM");

		UniversityDTO universityDTO1 = new UniversityDTO();
		universityDTO1.setId(2L);
		universityDTO1.setName("Technical University");
		universityDTO1.setShortName("UTM");

		UniversityDTO universityDTO2 = new UniversityDTO();
		universityDTO2.setId(3L);
		universityDTO2.setName("Technical University");
		universityDTO2.setShortName("UTM");

		UniversityDTO universityDTO3 = new UniversityDTO();
		universityDTO3.setId(4L);
		universityDTO3.setName("Technical University");
		universityDTO3.setShortName("UTM");

		List<UniversityDTO> universityDTOList = new ArrayList<>();
		universityDTOList.add(universityDTO);
		universityDTOList.add(universityDTO1);
		universityDTOList.add(universityDTO2);
		universityDTOList.add(universityDTO3);

		return universityDTOList;
	}

	private int getFivePlusFive(){
		return 5+5;
	}

}
