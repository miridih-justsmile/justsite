package comm.justsmile.justsite.springboot.web.controller.common;

interface Controller {
    /**
     * Controller 에서 최종적으로 호출할 페이지의 path를 반환한다.
     * 기본적으로 rootPath + 새로 정의한 path 를 사용.
     * @param path : 새로 정의된 path.
     * @return 최종적으로 정의된 path.
     */
    String resultPath(final String path);
}
