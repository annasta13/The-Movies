//
//  MovieDetailScreenContent.swift
//  iosApp
//
//  Created by Developer on 29/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieDetailScreenContent: View {
    var data: MovieDetailAndReview
    var onRefresh: () -> Void
    private let refreshName : String = "detailMovie"
    @ObservedObject var imageLoader = ImageLoaderService()
    @State private var image: UIImage = UIImage()
    @State private var reviewIdExpanded: String = ""
    
    var body: some View {
        let defaultImage = UIImage(named: "default_image")
        let movie = data.movieDetail
        let reviewList = data.review?.reviewData
        
        if (movie != nil){
            ScrollView{
                RefreshView(coordinateSpaceName: self.refreshName, onRefresh: onRefresh)
                VStack{
                Image(uiImage: image)
                    .resizable()
                    .clipped()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 200, height: 200)
                    .onReceive(imageLoader.$image) { image in
                        if (image.isImage()) {self.image = image}
                        else { self.image = defaultImage!}
                    }
                    .onAppear {
                        imageLoader.loadImage(for: movie!.poster)
                    }
                
                if (movie!.overview != nil){
                    Text(movie!.overview!).padding(.vertical)
                }
                
                HStack{
                    VStack(alignment: .leading){
                        Text("Release Date: \(movie!.releaseDate)").font(.caption).foregroundColor(.gray)
                        Text("Budget: \(movie!.budget)").font(.caption).foregroundColor(.gray)
                    }
                    Spacer()
                    VStack(alignment: .trailing){
                        Text("Popularity: \(movie!.popularity)").font(.caption).foregroundColor(.gray)
                        Text("Vote Average: \(movie!.voteAverage)").font(.caption).foregroundColor(.gray)
                    }
                }
                
                HStack{
                Text("Review").bold().padding(.vertical)
                    Spacer()
                }
                
                if (reviewList != nil){
                    ForEach(reviewList!, id: \.id) { review in
                        ZStack{
                            VStack(alignment: .leading){
                                HStack{
                                    AsyncImage(url: review.authorDetails.avatar.asUrl(),
                                               placeholder: { Image("default_profile").resizable().scaledToFit() },
                                               image: { Image(uiImage: $0).resizable() })
                                        .frame(width: 30, height: 30)
                                    Text(review.author).font(.caption)
                                }
                                Text(review.content)
                                    .font(.caption)
                                    .onTapGesture(perform: {setShowMore(reviewId: review.id)})
                                    .lineLimit(reviewIdExpanded == review.id ? 100 : 3)
                                HStack{
                                    Spacer()
                                    Text(reviewIdExpanded == review.id ? "See less" : "See more")
                                        .font(.caption).foregroundColor(.gray)
                                        .onTapGesture(perform: {setShowMore(reviewId: review.id)})
                                }
                            }.padding()
                        }.overlay(
                            RoundedRectangle(cornerRadius: 10)
                                .stroke(Color.gray, lineWidth: 1)
                        )
                    }
                }
                }.padding().padding(.top,30)
            }
            .navigationBarTitle(movie!.originalTitle)
            .coordinateSpace(name: self.refreshName)
        
            //.accentColor(Color.white)
        }
    }
    
    func setShowMore(reviewId: String){
        if (reviewId == reviewIdExpanded) { reviewIdExpanded = ""}
        else {reviewIdExpanded = reviewId}
    }
}

struct MovieDetailScreenContent_Previews: PreviewProvider {
    static var previews: some View {
        MovieDetailScreenContent(data: MovieDetailAndReview.companion.mocked, onRefresh: {})
    }
}
