package Lexicon.Reserve.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import Lexicon.Reserve.entity.Members;

@Service
public class UploadService {

	@Autowired
	private Members_Services_Dao members_Services;

	public List<Members> uploadFile(MultipartFile multipartFile) throws IOException {

		File file = convertMultiPartToFile(multipartFile);

		List<Members> mandatoryMissedList = new ArrayList<Members>();

		try (Reader reader = new BufferedReader(new FileReader(file));) {
			CsvToBean<Members> csvToBean = new CsvToBeanBuilder<Members>(reader).withType(Members.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<Members> membersList = csvToBean.parse();

			Iterator<Members> memberListClone = membersList.iterator();

			while (memberListClone.hasNext()) {
				Members member = memberListClone.next();

				if (member.getFirstName() == null || member.getFirstName().isEmpty() || member.getLastName() == null
						|| member.getLastName().isEmpty() || member.getEmail() == null || member.getEmail().isEmpty()
						|| member.getMobilePhone() == null || member.getMobilePhone().isEmpty()
						|| member.getAddress() == null || member.getAddress().isEmpty()) {
					mandatoryMissedList.add(member);
				}
			}

		}
		members_Services.saveAll(mandatoryMissedList);
		return members_Services.findAll();
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {

		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
