
$(document).ready(function() {
	
					var countryId = $("#country-hidden").val();
					var cityId = $("#city-hidden").val();

					var countrySelect;
					var citySelect;
					
					$.fn.select2.amd.require(['select2/compat/matcher'], function (oldMatcher) {
						countrySelect = $(".country-select").select2({
							placeholder : "Выберите страну",
							matcher: oldMatcher(matchStart),
							allowClear : true,
							data : countries,
							multiple : false
						}).on("select2:select", function(e) {
							if (countryId != e.params.data["id"]) {
								clearCities();
							}
							countryId = e.params.data["id"];
							if (countryId > 0) {
								$(".city-select").prop("disabled", false);
							} else {
								$(".city-select").prop("disabled", true);
							}
						}).on("select2:unselect", function(e) {
							clearCities();
							countryId = -1;
							$(".city-select").prop("disabled", true);
							$(this).data('state', 'unselected');
						}).on("select2:open", function(e) {
							//prevents select box from opening after clearing
						    if ($(this).data('state') === 'unselected') {
						        $(this).removeData('state'); 
						        var self = $(this);
						        setTimeout(function() {
						            self.select2('close');
						        }, 1);
						    }    
						});
						
						
						citySelect = $(".city-select").select2({
							placeholder : "Выберите город",
							matcher: oldMatcher(matchStart),
							disabled : true,
							allowClear : true,
							ajax : {
								url : function() {return "https://api.vk.com/method/database.getCities?country_id=" + countryId + "&lang=ru";},
								dataType : 'jsonp',
								delay : 250,
								data : function(params) {
									return {
										q: params.term,
										page : params.page
									};
								},
								processResults : function(data,	page) {
									return {
										results : $.map(data.response,
														function(item) {
															area = (item.area === undefined ? '' : (item.area));
															region = (item.region === undefined ? '' : (item.region));
															regionArea = '';
															if (area != '' || region != '') {
																regionArea = regionArea.concat("<p class='regionArea'>");
															}
															if (area != '') {
																regionArea = regionArea.concat(area);
															}
															if (area != '' && region != '') {
																regionArea = regionArea.concat("<br/>");
															}
															if (region != '') {
																regionArea = regionArea.concat(region);
															}
															
															if (area != '' || region != '') {
																regionArea = regionArea.concat("</p>");
															}
															return {
																text : item.title + regionArea,
																id : item.cid,
																title : item.title,
																area : area,
																region : region
															}
														})
									};

								},
								cache : true
							},
							escapeMarkup : function(markup) {
								return markup;
							},
					        templateSelection: formatDataSelection
						}).on("select2:select", function(e) {
							cityId = e.params.data["id"];
							cityName = e.params.data["title"];
						}).on("select2:unselect", function(e) {
							clearCities();
							$(this).data('state', 'unselected');
						}).on("select2:open", function(e) {
							//prevents select box from opening after clearing
						    if ($(this).data('state') === 'unselected') {
						        $(this).removeData('state'); 

						        var self = $(this);
						        setTimeout(function() {
						            self.select2('close');
						        }, 1);
						    }    
						});
						
						
						
					}); 
					
					$('#myModal').on('show.bs.modal', function () {
						
						if (countryId > 0) {
							countrySelect.val(countryId).trigger("change");
							citySelect.prop("disabled", false);
						}
						if (countryId > 0 && cityId > 0) {
							var option = new Option(cityName, cityId, true, true)
							citySelect.append(option);
							citySelect.trigger('change');
						}
					});
					
					$(".somediv").click(function() {
						console.log("|" + countryId + "|");
						console.log("|" + cityId + "|");
					});
					
					$('.userEditForm').submit(function() {
						$('#country-hidden').val(countryId);
						$('#city-hidden').val(cityId);
						$('#city-name-hidden').val(cityName);
						
					});
					
					
					function formatDataSelection (data) {
						if (data.title != '') {
							return data.title;
						} else {
							return data.text;
						}
					}
					
					function clearCities() {
						cityId = -1;
						$(".city-select").val(null).trigger("change");
					}
					
					function matchStart (term, text) {
						  if (text.toUpperCase().indexOf(term.toUpperCase()) == 0) {
						    return true;
						  }
						  return false;
					}
});