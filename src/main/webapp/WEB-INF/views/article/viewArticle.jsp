<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="../common/head.jsp" %>
</head>
<body>
<!-- start header -->
<header class="border-bottom py-3 mb-4">
  <%@include file="../common/header.jsp" %>
</header>
<!-- end header -->

<!-- start section -->
<div class='shadow m-lg-auto m-lg-5 m-5 m-auto p-lg-5 container-sm justify-content-center align-content-center'>
  <div class='notion-head m-auto'>
    <div class='notion-title'>
      <h1 class='h3 mb-3'>${article.title}</h1>
      <h1 class='h6'>${article.nickname} | ${article.createdDate}</h1>
    </div>
  </div>
  <hr>
  <div class='notion-body p-lg-5'>
    <div>
      <div class='notion-content'>
        ${article.content}
      </div>
    </div>
  </div>

  <c:if test="${isMine}">
  <div class='notion-footer mt-5 '>
    <a class='btn btn-outline-success' href="${root}/article/edit/${article.articleId}">수정하기</a>
    <a class='btn btn-outline-danger' href="${root}/article/${article.articleId}/remove">삭제하기</a>
  </div>
  </c:if>

  <div class='m-5 m-auto p-lg-5 container-sm justify-content-center align-content-center'>
    <div class='row justify-content-end'>
      <a href='${root}/article/list' type='button' class='col-1 btn btn-outline-info'>목록으로</a>
    </div>
  </div>
  <!-- end section -->
  <!-- 삭제 Modal -->
  <div class="modal fade" id="articleDeleteModal" tabindex="-1" aria-labelledby="articleDeleteModalLabel"
       aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="notificationDeleteModal">공지 삭제</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          공지를 삭제하겠습니까?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
          <a type="button" class="btn btn-danger" data-bs-target="#DeleteConfirmModal"
                  data-bs-toggle="modal" data-bs-dismiss="modal">삭제하기
          </a>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="DeleteConfirmModal" aria-hidden="true" aria-labelledby="DeleteConfirmModalLabel"
       tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="DeleteConfirmModal">Modal 2</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          삭제되었습니다.
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" data-bs-target="#exampleModalToggle" data-bs-toggle="modal"
                  data-bs-dismiss="modal">확인
          </button>
        </div>
      </div>
    </div>
  </div>
  <%-- 삭제 모달 --%>
</div>
<!-- start footer -->

<%@include file="../common/footer.jsp" %>
<!-- end footer -->
</body>
</html>
