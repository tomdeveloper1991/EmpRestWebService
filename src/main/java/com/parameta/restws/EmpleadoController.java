package com.parameta.restws;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parameta.model.dto.EmpleadoDTO;
import com.parameta.service.EmpleadoService;
import com.parameta.ws.model.*;

@RestController
public class EmpleadoController {

	@GetMapping("/guardarEmpleado")
	public RestResponse guardarEmpleado(
			@RequestParam(value = "numeroDocumento", defaultValue = "N/A") String numeroDocumento,
			@RequestParam(value = "nombres", defaultValue = "N/A") String nombres,
			@RequestParam(value = "apellidos", defaultValue = "N/A") String apellidos,
			@RequestParam(value = "tipo_doc", defaultValue = "N/A") String tipoDoc,
			@RequestParam(value = "fecha_nacimiento", defaultValue = "N/A") String fecha_nacimiento,
			@RequestParam(value = "fecha_vinculacion", defaultValue = "N/A") String fecha_vinculacion,
			@RequestParam(value = "cargo", defaultValue = "N/A") String cargo,
			@RequestParam(value = "salario", defaultValue = "N/A") String salario)
			throws DatatypeConfigurationException {

		EmpleadoService service = new EmpleadoService();
		EmpleadoDTO dto = new EmpleadoDTO();

		dto.setNumeroDocumento(numeroDocumento);
		dto.setNombres(nombres);
		dto.setApellidos(apellidos);
		dto.setTipoDoc(tipoDoc);
		dto.setFecha_nacimiento(fecha_nacimiento);
		dto.setFecha_vinculacion(fecha_vinculacion);
		dto.setCargo(cargo);
		dto.setSalario(salario);

		RestResponse restResponse = service.guardarEmpleadoService(dto);

		return restResponse;
	}
}
