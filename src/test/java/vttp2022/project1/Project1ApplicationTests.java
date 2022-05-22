package vttp2022.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;

@AutoConfigureMockMvc
@SpringBootTest
class Project1ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getLoginPage() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/login")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	public void getShoppingPage() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/shop")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	public void searchShoppingPage1() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/search")
		.accept(MediaType.TEXT_HTML)
		.queryParam("name", "")
		.queryParam("category","All");

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	public void searchShoppingPage2() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/search")
		.accept(MediaType.TEXT_HTML)
		.queryParam("name", "top")
		.queryParam("category","crop top");

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	public void registerPage() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/register")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	public void shopProduct() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/shop/product/1")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@BeforeEach
	public void loginSuccess() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.post("/login")
		.accept(MediaType.APPLICATION_FORM_URLENCODED)
		.queryParam("name", "ben")
		.queryParam("password", "ben");

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(302, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void adminCategory() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/category")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void adminProduct() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/product")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void cart() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/cart")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void myAccount() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/myaccount")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	// @Test
	// @WithMockUser(username="yumin",roles={"ADMIN"})
	// public void addCategory() throws Exception {

	// 	RequestBuilder req = MockMvcRequestBuilders.post("/admin/category")
	// 	.accept(MediaType.APPLICATION_FORM_URLENCODED)
	// 	.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	// 	.param("name", "yoga mat")
	// 	.param("description", "yoga mat");

	// 	MvcResult result = mvc.perform(req).andReturn();
	// 	int status = result.getResponse().getStatus();

	// 	Assertions.assertEquals(200, status);
	// }

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void deleteCategory() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/category/delete/4")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void editCategory() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/category/edit/1")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void updateCategory() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.post("/admin/category/updatecategory")
		.accept(MediaType.APPLICATION_FORM_URLENCODED)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		.param("name","Bra")
		.param("description","Bra")
		.param("id","1");

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void editProduct() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/product/edit/1")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void deleteProduct() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/product/delete/10")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void paidOrderItems() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/myaccount/items/1")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void customerOrders() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/order")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void customerOrderItems() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/admin/order/items/1")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void addToCart() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.post("/addToCart")
		.accept(MediaType.TEXT_HTML)
		.queryParam("prod_id", "1")
		.queryParam("size","xs")
		.queryParam("quantity","1");

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void image() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/shop/image/1")
		.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	// @Test
	// public void registerSuccess() throws Exception {

	// 	RequestBuilder req = MockMvcRequestBuilders.post("/register/success")
	// 	.accept(MediaType.APPLICATION_FORM_URLENCODED)
	// 	.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	// 	.param("username", "david")
	// 	.param("password","david")
	// 	.param("name","david")
	// 	.param("address","singapore")
	// 	.param("number","123456");

	// 	MvcResult result = mvc.perform(req).andReturn();
	// 	int status = result.getResponse().getStatus();

	// 	Assertions.assertEquals(200, status);
	// }

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void updateCart() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.get("/cart/update/22")
		.accept(MediaType.TEXT_HTML)
		.queryParam("quantity", "1");

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	// @Test
	// @WithMockUser(username="yumin",roles={"ADMIN"})
	// public void removeCartItem() throws Exception {

	// 	RequestBuilder req = MockMvcRequestBuilders.get("/cart/remove/41")
	// 	.accept(MediaType.TEXT_HTML);

	// 	MvcResult result = mvc.perform(req).andReturn();
	// 	int status = result.getResponse().getStatus();

	// 	Assertions.assertEquals(200, status);
	// }

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void checkOut() throws Exception {

		RequestBuilder req = MockMvcRequestBuilders.post("/checkout")
		.accept(MediaType.TEXT_HTML);
		
		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void addProduct() throws Exception {
		String filePath = "src/test/java/vttp2022/project1/item4.webp";
		File f = new File(filePath);
		FileInputStream fi1 = new FileInputStream(f);
		MockMultipartFile fstmp = new MockMultipartFile("image", "item4.webp", "multipart/form-data",fi1);
		RequestBuilder req = MockMvcRequestBuilders
		.multipart("/admin/product")
        .file(fstmp)
		.accept(MediaType.TEXT_HTML_VALUE)
		.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
		.queryParam("name", "Crop Top")
		.queryParam("description", "Crop Top")
		.queryParam("price", "44")
		.queryParam("category", "Crop Top");
		
		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	@WithMockUser(username="yumin",roles={"ADMIN"})
	public void updateProduct() throws Exception {
		String filePath = "src/test/java/vttp2022/project1/item4.webp";
		File f = new File(filePath);
		FileInputStream fi1 = new FileInputStream(f);
		MockMultipartFile fstmp = new MockMultipartFile("image", "item4.webp", "multipart/form-data",fi1);
		RequestBuilder req = MockMvcRequestBuilders
		.multipart("/admin/product/updateproduct")
        .file(fstmp)
		.accept(MediaType.TEXT_HTML_VALUE)
		.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
		.queryParam("name", "Crop Top")
		.queryParam("description", "Crop Top")
		.queryParam("price", "44")
		.queryParam("category", "Crop Top")
		.queryParam("id", "8");
		
		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}


















}
