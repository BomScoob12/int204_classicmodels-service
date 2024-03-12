package sit.int204.classicmodelsservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import sit.int204.classicmodelsservice.dtos.PageDto;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private static final ModelMapper modelMapper = new ModelMapper();

    private ListMapper() {
    }

    public static ListMapper getInstance() {
        return listMapper;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> PageDto<T> toPageDTO(Page<S> source, Class<T> targetClass,
                                       ModelMapper modelMapper) {
        PageDto<T> page = modelMapper.map(source, PageDto.class);
        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return page;
    }

    public <S, T> PageDto<T> toPageDTO(Page<S> source, Class<T> targetClass) {
        PageDto<T> page = modelMapper.map(source, PageDto.class);
        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return page;
    }
}
